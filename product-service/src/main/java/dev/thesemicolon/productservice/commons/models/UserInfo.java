package dev.thesemicolon.productservice.commons.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserInfo implements Serializable {

  String userID;
}

