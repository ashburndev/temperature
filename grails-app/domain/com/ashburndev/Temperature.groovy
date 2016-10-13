package com.ashburndev

class Temperature {

  Double value
  String location
  Date dateCreated
  Date lastUpdated

  static constraints = {
    value nullable: false
    location nullable: true
    dateCreated nullable: true
    lastUpdated nullable: true
  }
}
