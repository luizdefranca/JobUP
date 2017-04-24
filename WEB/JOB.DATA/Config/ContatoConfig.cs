using JOB.DATA.Domain;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class ContatoConfig : EntityTypeConfiguration<CONTATO>
    {
        public ContatoConfig()
        {
            HasKey(c => c.ID_USUARIO);

            Property(c => c.ID_USUARIO)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);

            Property(p => p.FIXO.NrTelefone)
                .IsOptional()
                .HasColumnName("FIXO")
                .HasMaxLength(14);

            Property(p => p.CELULAR.NrTelefone)
                .IsOptional()
                .HasColumnName("CELULAR")
                .HasMaxLength(14);

            Property(p => p.EMAIL.EMail)
                .HasColumnName("EMAIL")
                .IsRequired();
        }
    }
}