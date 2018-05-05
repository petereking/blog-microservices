package com.chizi.blog.web.component;

import com.github.jknack.handlebars.Options;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

import java.io.IOException;
import java.util.Objects;

@HandlebarsHelper
public class HandlebarsHelpers {
  public CharSequence owner(Long authorId, Long userId, Options options) throws IOException {
    if (Objects.equals(authorId, userId)) {
      return options.fn();
    } else {
      return options.inverse();
    }
  }
}
