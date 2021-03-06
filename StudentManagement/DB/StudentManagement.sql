USE [StudentManagement]
GO
/****** Object:  Table [dbo].[Students]    Script Date: 11/03/2016 12:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Students](
	[Serial] [int] NOT NULL,
	[ID] [varchar](20) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Email] [varchar](70) NOT NULL,
	[Mobile] [varchar](20) NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 11/03/2016 12:53:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Admin](
	[Username] [varchar](10) NOT NULL,
	[Password] [int] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
