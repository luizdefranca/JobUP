using JOB.DATA.Domain;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class EnderecoConfig : EntityTypeConfiguration<ENDERECO>
    {
        public EnderecoConfig()
        {
            HasKey(c => c.ID_USUARIO);

            Property(c => c.ID_USUARIO)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);

            Property(p => p.LOGRADOURO)
                .IsRequired();

            Property(p => p.CEP)
                .IsRequired()
                .HasMaxLength(8);

            Property(p => p.BAIRRO)
                .IsRequired();

            Property(p => p.CIDADE)
                .IsRequired();
        }
    }
}