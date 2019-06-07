package core.ui.controller;

import lombok.Value;

interface Controller {

  @Value
  class CreatedResponse {

    private String id;
  }

}

