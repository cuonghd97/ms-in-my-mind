package dev.thesemicolon.orderservice.commons.models;

import lombok.Data;

@Data
public class RequestInfo {
  String token;
  String requestId;
}
