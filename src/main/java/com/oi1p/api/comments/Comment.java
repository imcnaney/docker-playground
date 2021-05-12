package com.oi1p.api.comments;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Entity
@RegisterForReflection
public class Comment extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  public Long id;

  public String comment;
}
