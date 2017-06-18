using Newtonsoft.Json;
using System;

namespace JOB.DATA.Domain
{
    public class HISTORICO_MOEDA
    {
        public Guid ID_USUARIO { get; set; }
        public DateTime DT_MOVIMENTACAO { get; set; }
        public Int16 VALOR_MOVIMENTADO { get; set; }
        public string MOTIVO { get; set; }

        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected HISTORICO_MOEDA()
        {
        }

        public HISTORICO_MOEDA(Guid ID_USUARIO, Int16 VALOR, string MOTIVO)
        {
            this.DT_MOVIMENTACAO = DateTime.Now;

            this.ID_USUARIO = ID_USUARIO;
            this.VALOR_MOVIMENTADO += VALOR;
            this.MOTIVO = MOTIVO;
        }

        public USUARIO USUARIO { get; set; }
    }
}