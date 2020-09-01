# # Code Challenge Pawel Piwowarczyk


This is a code challenge API documentation.

  - You can use this document.
  - Also under the: http://localhost:8080/swagger-ui.htmlr you can find swagger api documentation.

# API

 Message api:
 - GET: /massage/myMessages

Parameters:
| Name | Description |
| ------ | ------ |
| user (string)| Name of the user messages we want to recive |

Retruns list of chronologicly reversed messages posted by a User.
It will return error message if User is not registered in the system.

- GET: /message/myWall

Parameters:
| Name | Description |
| ------ | ------ |
| user (string) | Name of the user whos followers messages we want to recive |

Retruns list of chronologicly reversed messages posted by a User followers.
It will return error message if User is not registered in the system.

- POST: /message/newMessage

Parameters:
| Name | Description |
| ------ | ------ |
| user (string) | Name of the user who want to post a message |
| messageText (string) (body) | Text of the message we want to post |

Returns confirmation message after posting a new message.
If user is not present in the system he is automaticly registered.
Error is returned if we post to long of a message (More than 140 characters).

User api:

- GET /user/follow/{creatorName}

Parameters
| Name | Description |
| ------ | ------ |
| user (string) | Name of the user whos followers messages we want to recive |
| creatorName (string)(path) | Name of the user who we want to follow |

Returns confirmation message and you start following a creator.
Will return error when User wants to follow himself also if creator or user are not registered in the system.
