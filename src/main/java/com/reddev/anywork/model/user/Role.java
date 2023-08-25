package com.reddev.anywork.model.user;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@RequiredArgsConstructor
public enum Role {

  USER(Sets.newHashSet()),
  WORKER(Sets.newHashSet());

  @Getter
  private final Set<Permission> permissions;

}
