﻿using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class ChatConfig : EntityTypeConfiguration<CHAT>
    {
        public ChatConfig()
        {
            HasKey(c => new { c.ID_SERVICO, c.DT_MENSAGEM });

            Property(p => p.MENSAGEM)
               .IsRequired();
        }
    }
}