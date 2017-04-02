using System;

namespace JOB.DATA.Domain.OFERTAS
{
    internal class JOB_OFERTA
    {
        public int ID_OFERTA { get; set; }
        public int ID_USUARIO_PROFISSIONAL { get; set; }
        public DateTime DT_CONFIRMACAO { get; set; }
        public double? VALOR_JOB { get; set; }
        public String OBSERVACOES { get; set; }
    }
}