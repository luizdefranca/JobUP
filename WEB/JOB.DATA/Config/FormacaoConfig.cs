using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class FormacaoConfig : EntityTypeConfiguration<FORMACAO>
    {
        public FormacaoConfig()
        {
            HasKey(c => new { c.ID_USUARIO, c.ID_ESPECIALIDADE, c.ID_FORMACAO });

            Property(p => p.INSTITUICAO)
               .IsRequired();

            Property(p => p.NOME_CURSO)
                .IsRequired();

            Property(p => p.ANO_FORMACAO)
                .IsRequired();

            Property(p => p.DT_APROVACAO)
                .IsOptional();

            Property(p => p.APROVADO)
                .IsRequired();
        }
    }
}