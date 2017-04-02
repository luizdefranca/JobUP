using System;

namespace JOB.DATA.Domain.OFERTAS
{
    internal class PROPOSTA
    {
        public int ID_OFERTA { get; set; }
        public int ID_USUARIO_PROFISSIONAL { get; set; }
        public DateTime DT_PROPOSTA { get; set; }
        public double? VALOR_PROPOSTA { get; set; }
        public bool PROPOSTA_ACEITA { get; set; }
    }
}