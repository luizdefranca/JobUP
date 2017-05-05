USE [mundoup]
GO

/****** Object:  Table [jobup].[OFERTA_SERVICO]    Script Date: 05/05/2017 20:16:54 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [jobup].[OFERTA_SERVICO](
	[ID_SERVICO] [uniqueidentifier] NOT NULL,
	[ID_USUARIO] [uniqueidentifier] NOT NULL,
	[DT_OFERTA] [datetime] NOT NULL,
	[ACEITA] [bit] NULL,
 CONSTRAINT [PK_jobup.OFERTA_SERVICO] PRIMARY KEY CLUSTERED 
(
	[ID_SERVICO] ASC,
	[ID_USUARIO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)

GO

ALTER TABLE [jobup].[OFERTA_SERVICO]  WITH CHECK ADD  CONSTRAINT [FK_jobup.OFERTA_SERVICO_jobup.SERVICO_ID_SERVICO] FOREIGN KEY([ID_SERVICO])
REFERENCES [jobup].[SERVICO] ([ID_SERVICO])
ON DELETE CASCADE
GO

ALTER TABLE [jobup].[OFERTA_SERVICO] CHECK CONSTRAINT [FK_jobup.OFERTA_SERVICO_jobup.SERVICO_ID_SERVICO]
GO

ALTER TABLE [jobup].[OFERTA_SERVICO]  WITH CHECK ADD  CONSTRAINT [FK_jobup.OFERTA_SERVICO_jobup.USUARIO_ID_USUARIO] FOREIGN KEY([ID_USUARIO])
REFERENCES [jobup].[USUARIO] ([ID_USUARIO])
GO

ALTER TABLE [jobup].[OFERTA_SERVICO] CHECK CONSTRAINT [FK_jobup.OFERTA_SERVICO_jobup.USUARIO_ID_USUARIO]
GO

