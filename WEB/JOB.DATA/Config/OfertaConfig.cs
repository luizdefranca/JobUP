using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class OfertaConfig : EntityTypeConfiguration<OFERTA_SERVICO>
    {
        public OfertaConfig()
        {
            HasKey(c => new { c.ID_SERVICO, c.ID_USUARIO });
        }
    }
}