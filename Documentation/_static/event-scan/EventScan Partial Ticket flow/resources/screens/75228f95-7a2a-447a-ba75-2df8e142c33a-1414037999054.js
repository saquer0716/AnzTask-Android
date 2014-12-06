jQuery("#simulation")
  .on("click", ".s-75228f95-7a2a-447a-ba75-2df8e142c33a .click", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Collect_Button")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-confirm_dialog",
                    "effect": {
                      "type": "fade",
                      "easing": "linear",
                      "duration": 200
                    }
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-ticket_3")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_flag_3"
                },"1" ]
              },
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-check_3"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_3",
                    "value": "0"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimMinus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT"
                  }
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-check_3"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_3",
                    "value": "1"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimPlus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                }
              ]
            }
          ]
        },
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_total"
                },"5" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT ALL"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-ticket_4")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_flag_4"
                },"1" ]
              },
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-check_4"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_4",
                    "value": "0"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimMinus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT"
                  }
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-check_4"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_4",
                    "value": "1"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimPlus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                }
              ]
            }
          ]
        },
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_total"
                },"5" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT ALL"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-ticket_5")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_flag_5"
                },"1" ]
              },
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-check_5"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_5",
                    "value": "0"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimMinus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT"
                  }
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-check_5"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_5",
                    "value": "1"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimPlus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                }
              ]
            }
          ]
        },
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_total"
                },"5" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT ALL"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-ticket_1")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_flag_1"
                },"1" ]
              },
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-check_1"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_1",
                    "value": "0"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimMinus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT"
                  }
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-check_1"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_1",
                    "value": "1"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimPlus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                }
              ]
            }
          ]
        },
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_total"
                },"5" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT ALL"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-ticket_2")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_flag_2"
                },"1" ]
              },
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-check_2"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_2",
                    "value": "0"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimMinus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT"
                  }
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-check_2"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_flag_2",
                    "value": "1"
                  }
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": "check_total",
                    "value": {
                      "action": "jimPlus",
                      "parameter": [ {
                        "datatype": "variable",
                        "element": "check_total"
                      },"1" ]
                    }
                  }
                }
              ]
            }
          ]
        },
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "check_total"
                },"5" ]
              },
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Collect_Button",
                    "value": "COLLECT ALL"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Label_86")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "isbackward": true,
                    "transition": "slidedown"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Label_18")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-confirm_dialog"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Label_19")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": "#s-confirm_dialog"
                  }
                },
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": "#s-Loading_Overlay"
                  }
                },
                {
                  "action": "jimPause",
                  "parameter": {
                    "pause": 1000
                  }
                },
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/2a0258c0-4050-426a-823d-0cd837204d7d"
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    }
  })
  .on("pageload", ".s-75228f95-7a2a-447a-ba75-2df8e142c33a .pageload", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Label_7")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "target": "#s-Label_7",
                    "value": {
                      "action": "jimConcat",
                      "parameter": [ {
                        "action": "jimSubstring",
                        "parameter": [ {
                          "action": "jimSystemTime"
                        },"0","5" ]
                      }," PM" ]
                    }
                  }
                }
              ]
            }
          ]
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    }
  });