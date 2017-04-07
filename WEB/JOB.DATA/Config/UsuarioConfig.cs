using JOB.DATA.Domain;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.Infrastructure.Annotations;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class UsuarioConfig : EntityTypeConfiguration<USUARIO>
    {
        public UsuarioConfig()
        {
            HasKey(c => c.ID_USUARIO);

            Property(c => c.ID_USUARIO)
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(p => p.NOME)
                .HasMaxLength(100)
                .IsRequired();

            Property(p => p.CPF.NR)
                .HasColumnName("CPF")
                .HasMaxLength(11)
                .IsRequired()
                .HasColumnAnnotation(IndexAnnotation.AnnotationName, new IndexAnnotation(new IndexAttribute("IX_Usuario_CPF", 1) { IsUnique = true }));

            Property(p => p.RG.NR)
                .HasMaxLength(8)
                .IsRequired();

            HasOptional(n => n.ENDERECO)
                .WithRequired(n => n.USUARIO)
                .WillCascadeOnDelete(true);

            HasOptional(n => n.CONTATO)
                .WithRequired(n => n.USUARIO)
                .WillCascadeOnDelete(true);

            HasMany(n => n.PERFIS_PROFISSIONAIS)
                .WithRequired(n => n.USUARIO)
                .HasForeignKey(n => n.ID_USUARIO)
                .WillCascadeOnDelete(true);
        }
    }
}