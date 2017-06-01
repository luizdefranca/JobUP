using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class HistoricoMoedaConfig : EntityTypeConfiguration<HISTORICO_MOEDA>
    {
        public HistoricoMoedaConfig()
        {
            HasKey(c => new { c.ID_USUARIO, c.DT_MOVIMENTACAO });
        }
    }
}