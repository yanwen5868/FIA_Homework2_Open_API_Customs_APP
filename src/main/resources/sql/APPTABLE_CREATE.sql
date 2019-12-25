USE [PCIS]
GO

DROP TABLE IF EXISTS PeriodRate;

/****** Object:  Table [dbo].[PeriodRate]    Script Date: 2019/12/20 下午 03:40:44 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PeriodRate](
	[currency] [varchar](6) NULL,
	[year] [int] NULL,
	[month] [int] NULL,
	[period] [int] NULL,
	[buyPrice] [float] NULL,
	[sellPrice] [float] NULL
) ON [CIS_DATA]
GO
