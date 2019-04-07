USE [WebSecProject]
GO

/****** Object:  Table [dbo].[Users]    Script Date: 29.03.2019 12:36:36 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Users](
	[Username] [nchar](20) NOT NULL,
	[HashedPassword] [nchar](128) NOT NULL,
	[Salt] [varbinary](50) NOT NULL,
	[Groups] [nchar](7)
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE Users ADD LastPost DATETIME
UPDATE Users SET LastPost = (SELECT GETDATE())		-- Initial setup, will create a trigger to automatically update this value.

ALTER TABLE Users ADD Posts INT
UPDATE Users SET Posts = 100;						-- Proof of concept, currently doesn't do anything, but will do later in the project

select * from Users

-- Automatically initilize newly created Users
CREATE TRIGGER initialize ON Users
AFTER INSERT
as
begin

	DECLARE @iun nchar(20);
	SELECT @iun = Username FROM inserted;

	UPDATE Users SET LastPost = GETDATE(), Posts = 0 WHERE Username = @iun;
end

CREATE TABLE Posts (

	PostID int IDENTITY(1,1) PRIMARY KEY,
	Posted_by nchar(20), 
	GroupID nchar(7) NOT NULL,
	PostHeader nchar(100) NOT NULL,
	PostBody nchar(2000),

	FOREIGN KEY (Posted_by) REFERENCES Users(Username) ON DELETE SET NULL
);

ALTER TABLE Posts ADD Deleted int;
Update Posts Set Deleted = 0;

ALTER TABLE Posts ADD LastPost DATETIME
UPDATE Posts SET LastPost = (SELECT GETDATE())		-- Initial setup, will create a trigger to automatically update this value.

insert into Posts values('zzxxccvvbb', '1,1,1,1', '177013', 'shimashimarinrin', 0, '');
insert into Posts values('zzxxccvvbb', '1,1,1,1', 'animetirries', 'shimashimarriririrriinrin', 0, '');
insert into Posts values('zzxxccvvbb', '1,1,1,1', 'endro', 'mumumumumumumumumu', 0, '');	

--Automatically increase post number of user and last post date
--This trigger will work the same way with both the posts and the comments
CREATE TRIGGER update_user_p ON Posts
AFTER INSERT
as
begin

	DECLARE @iun nchar(20);
	DECLARE @post_id int;
	SELECT @iun = Posted_by, @post_id = PostID FROM inserted;

	UPDATE Users SET LastPost = GETDATE(), Posts = Posts + 1 WHERE Username = @iun; 
	UPDATE Comments SET LastPost = GETDATE() WHERE PostID = @post_id;

end

select * from Posts

DELETE FROM Posts WHERE PostID > 10

CREATE TABLE Comments (

	CommentID int IDENTITY(1,1) PRIMARY KEY,
	PostID int,
	Posted_by nchar(20),
	CommentBody nchar(2000) NOT NULL,

	FOREIGN KEY	(PostID) REFERENCES Posts(PostID) ON DELETE SET NULL,
	FOREIGN KEY (Posted_by) REFERENCES Users(Username) ON DELETE SET NULL
);

ALTER TABLE Comments ADD Deleted int;
UPDATE Comments SET Deleted = 0;


ALTER TABLE Comments ADD LastPost DATETIME
UPDATE Comments SET LastPost = (SELECT GETDATE())		-- Initial setup, will create a trigger to automatically update this value.

--Automatically increase post number of user and last post date
--This trigger will work the same way with both the posts and the comments
CREATE TRIGGER update_user_c ON Comments
AFTER INSERT
as
begin

	DECLARE @iun nchar(20);
	DECLARE @cmt_id int;
	SELECT @iun = Posted_by, @cmt_id = CommentID FROM inserted;

	UPDATE Users SET LastPost = GETDATE(), Posts = Posts + 1 WHERE Username = @iun; 
	UPDATE Comments SET LastPost = GETDATE() WHERE CommentID = @cmt_id;

end

insert into Comments values(1, 'zzxxccvvbb', '~~', 0, '');

select * from Comments 