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

        public Guid ID_SERVICO { get; private set; }
        public Guid ID_USUARIO { get; private set; }
        public DateTime DT_OFERTA { get; private set; }
        public bool? ACEITA { get; private set; }

        public SERVICO SERVICO { get; private set; }
        public USUARIO USUARIO { get; private set; }
    }
}