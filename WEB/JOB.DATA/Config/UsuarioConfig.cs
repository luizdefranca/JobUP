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
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);

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

            //HasOptional(n => n.ENDERECO)
            //    .WithRequired(n => n.USUARIO)
            //    .WillCascadeOnDelete(true);

            //HasOptional(n => n.CONTATO)
            //    .WithRequired(n => n.USUARIO)
            //    .WillCascadeOnDelete(true);

            Property(p => p.LOGRADOURO)
                .IsRequired();

            Property(p => p.CEP)
                .IsRequired()
                .HasMaxLength(8);

            Property(p => p.BAIRRO)
                .IsRequired();

            Property(p => p.CIDADE)
                .IsRequired();

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


            HasMany(n => n.PERFIS_PROFISSIONAIS)
                .WithRequired(n => n.USUARIO)
                .HasForeignKey(n => n.ID_USUARIO)
                .WillCascadeOnDelete(true);

            HasMany(n => n.OFERTAS_SERVICO)
                .WithRequired(n => n.USUARIO)
                .HasForeignKey(n => n.ID_USUARIO);

            HasMany(n => n.PROPOSTAS_SERVICO)
                .WithRequired(n => n.USUARIO)
                .HasForeignKey(n => n.ID_USUARIO);
        }
    }
}