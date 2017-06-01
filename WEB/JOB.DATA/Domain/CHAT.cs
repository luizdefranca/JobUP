using JOB.HELPERS.Validation;
using Newtonsoft.Json;
using System;

namespace JOB.DATA.Domain
{
    public class CHAT
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected CHAT()
        {
            this.InicializaVariaveis();
        }

        public CHAT(Guid ID_SERVICO, Guid ID_USUARIO, string MENSAGEM)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(MENSAGEM, "MENSAGEM");

            this.InicializaVariaveis();

            this.ID_SERVICO = ID_SERVICO;
            this.ID_USUARIO = ID_USUARIO;
            this.DT_MENSAGEM = DateTime.Now;
            this.MENSAGEM = MENSAGEM;
        }

        public void MarcarComoLida()
        {
            this.LIDA = true;
        }

        private void InicializaVariaveis()
        {
            //this.PERFIS_PROFISSIONAIS = new HashSet<PERFIL_PROFISSIONAL>();
        }

        public Guid ID_SERVICO { get; private set; }
        public DateTime DT_MENSAGEM { get; private set; }
        public Guid ID_USUARIO { get; private set; }
        public string MENSAGEM { get; private set; }
        public bool LIDA { get; private set; }
    }
}