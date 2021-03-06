package com.chizi.blog.post.event;

import com.chizi.blog.post.model.Post;
import com.chizi.blog.post.repo.PostRepo;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class PostVisitedListener {
  @Autowired
  private EventBus eventBus;

  @PostConstruct
  private void init() {
    eventBus.register(this);
  }

  @Autowired
  private PostRepo postRepo;

  @Subscribe
  public void onPostVisited(PostVisitedEvent event) {
    log.info("user({}) visit post({})", event.getVisitorId(), event.getPostId());

    Post post = postRepo.findOne(event.getPostId());
    post.setPv(post.getPv() + 1);

    postRepo.save(post);
  }
}
