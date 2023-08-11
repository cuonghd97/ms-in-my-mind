package dev.thesemicolon.orderservice.commons.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field.Str;

@Data
@AllArgsConstructor
public class UserInfo implements Serializable {
  String userID;
  String token;
}
