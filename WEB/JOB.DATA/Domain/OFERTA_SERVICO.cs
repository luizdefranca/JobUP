using Newtonsoft.Json;
using System;

namespace JOB.DATA.Domain
{
    public class OFERTA_SERVICO
    {
        [JsonConstructor]
        protected OFERTA_SERVICO()
        {
            this.InicializaVariaveis();
        }

        /// <summary>
        ///
        /// </summary>
        /// <param name="ID_SERVICO"></param>
        /// <param name="ID_USUARIO">ID DO PROFISSIONAL (EXCLUSIVO SERVIÇO PRIVADO)</param>
        public OFERTA_SERVICO(Guid ID_SERVICO, Guid ID_USUARIO)
        {
            this.InicializaVariaveis();

            this.DT_OFERTA = DateTime.Now;
            this.ID_SERVICO = ID_SERVICO;
            this.ID_USUARIO = ID_USUARIO;
        }

        private void InicializaVariaveis()
        {
            //this.OFERTAS = new HashSet<OFERTA_SERVICO>();
            //this.PROPOSTAS = new HashSet<PROPOSTA_SERVICO>();
        }

        public void AceitarOferta()
        {
            this.ACEITA = true;
        }

        public void RejeitarOferta()
        {
            this.ACEITA = false;
        }

        public Guid ID_SERVICO { get; set; }
        public Guid ID_USUARIO { get; set; }
        public DateTime DT_OFERTA { get; set; }
        public bool? ACEITA { get; set; }

        public SERVICO SERVICO { get; set; }
        public USUARIO USUARIO { get; set; }
    }
}