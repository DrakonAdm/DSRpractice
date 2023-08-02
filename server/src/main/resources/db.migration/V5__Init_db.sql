CREATE TABLE chatRoom (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    chatId VARCHAR(255) NOT NULL,
    senderId VARCHAR(255) NOT NULL,
    recipientId VARCHAR(255) NOT NULL
);

CREATE TABLE chatNotification (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    senderId VARCHAR(255) NOT NULL,
    senderName VARCHAR(255) NOT NULL
)

CREATE TABLE chatMessage (
                             id VARCHAR(36) NOT NULL PRIMARY KEY,
                             chatId VARCHAR(36) NOT NULL,
                             senderId VARCHAR(36) NOT NULL,
                             recipientId VARCHAR(36) NOT NULL,
                             senderName VARCHAR(255) NOT NULL,
                             recipientName VARCHAR(255) NOT NULL,
                             content TEXT,
                             timestamp TIMESTAMP,
                             status VARCHAR(20)
);
