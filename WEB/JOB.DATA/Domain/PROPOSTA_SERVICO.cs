using Newtonsoft.Json;
using System;
using JOB.DATA.Enum;

namespace JOB.DATA.Domain
{
    public class PROPOSTA_SERVICO
    {
        [JsonConstructor]
        protected PROPOSTA_SERVICO()
        {
            this.InicializaVariaveis();
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="ID_SERVICO"></param>
        /// <param name="ID_USUARIO">ID DO PROFISSIONAL</param>
        /// <param name="VL_PROPOSTA"></param>
        /// <param name="JUSTIFICATIVA"></param>
        /// <param name="DURACAO_SERVICO"></param>
        /// <param name="VALOR_DURACAO_SERVICO"></param>
        public PROPOSTA_SERVICO(Guid ID_SERVICO, Guid ID_USUARIO, double VL_PROPOSTA, string JUSTIFICATIVA, EnumDuracaoServico DURACAO_SERVICO, int VALOR_DURACAO_SERVICO)
        {
            this.InicializaVariaveis();

            this.DT_PROPOSTA = DateTime.Now;

            this.ID_SERVICO = ID_SERVICO;
            this.ID_USUARIO = ID_USUARIO;
            this.VL_PROPOSTA = VL_PROPOSTA;
            this.JUSTIFICATIVA = JUSTIFICATIVA;
            this.DURACAO_SERVICO = DURACAO_SERVICO;
            this.VALOR_DURACAO_SERVICO = VALOR_DURACAO_SERVICO;
        }

        public void AceitarProposta()
        {
            this.ACEITA = true;
        }

        private void InicializaVariaveis()
        {
            //this.OFERTAS = new HashSet<OFERTA_SERVICO>();
            //this.PROPOSTAS = new HashSet<PROPOSTA_SERVICO>();
        }

        public Guid ID_SERVICO { get; set; }
        public Guid ID_USUARIO { get; set; }
        public DateTime DT_PROPOSTA { get; set; }
        public EnumDuracaoServico DURACAO_SERVICO { get; set; }
        public int VALOR_DURACAO_SERVICO { get; set; }
        public double VL_PROPOSTA { get; set; }
        public string JUSTIFICATIVA { get; set; }
        public bool ACEITA { get; set; }

        public SERVICO SERVICO { get; set; }
        public USUARIO USUARIO { get; set; }
    }
}