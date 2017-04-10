using AgendaCirurgicaBeta.Domain.Core;
using JOB.DATA.ValueObject;
using Newtonsoft.Json;

namespace JOB.DATA.Domain
{
    public class CONTATO : EntityBase
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected CONTATO()
        {
        }

        public CONTATO(int ID_USUARIO, Telefone FIXO, Telefone CELULAR, Email EMAIL)
        {
            this.ID_USUARIO = ID_USUARIO;
            if (FIXO != null) this.FIXO = FIXO;
            this.CELULAR = CELULAR;
            this.EMAIL = EMAIL;
        }

        public void AtualizarValor(Telefone FIXO, Telefone CELULAR, Email EMAIL)
        {
            if (FIXO != null) this.FIXO = FIXO;
            this.CELULAR = CELULAR;
            this.EMAIL = EMAIL;
        }

        public int ID_USUARIO { get; private set; }
        public Telefone FIXO { get; private set; }
        public Telefone CELULAR { get; private set; }
        public Email EMAIL { get; private set; }

        public USUARIO USUARIO { get; private set; }
    }
}