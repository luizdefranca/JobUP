using AgendaCirurgicaBeta.Domain.Core;
using JOB.DATA.Enum;
using JOB.HELPERS.Validation;
using Newtonsoft.Json;

namespace JOB.DATA.Domain
{
    public class ENDERECO: EntityBase
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected ENDERECO()
        {
            this.InicializaVariaveis();
        }

        public ENDERECO(int ID_USUARIO, EnumUF UF, string CEP, string LOGRADOURO, string COMPLEMENTO, string BAIRRO, string CIDADE)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(CEP, "CEP");
            AssertionConcern.AssertArgumentNotEmptyNotNull(LOGRADOURO, "LOGRADOURO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(BAIRRO, "BAIRRO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(CIDADE, "CIDADE");

            this.InicializaVariaveis();

            this.ID_USUARIO = ID_USUARIO;
            this.UF = UF;
            this.CEP = CEP;
            this.LOGRADOURO = LOGRADOURO;
            this.COMPLEMENTO = COMPLEMENTO;
            this.BAIRRO = BAIRRO;
            this.CIDADE = CIDADE;
        }

        public void AtualizaValores(EnumUF UF, string CEP, string LOGRADOURO, string COMPLEMENTO, string BAIRRO, string CIDADE)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(CEP, "CEP");
            AssertionConcern.AssertArgumentNotEmptyNotNull(LOGRADOURO, "LOGRADOURO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(BAIRRO, "BAIRRO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(CIDADE, "CIDADE");

            this.UF = UF;
            this.CEP = CEP;
            this.LOGRADOURO = LOGRADOURO;
            this.COMPLEMENTO = COMPLEMENTO;
            this.BAIRRO = BAIRRO;
            this.CIDADE = CIDADE;
        }

        private void InicializaVariaveis()
        {
            //this.GUIAS = new HashSet<GUIA>();
        }

        public int ID_USUARIO { get; private set; }
        public EnumUF UF { get; private set; }
        public string CEP { get; private set; }
        public string LOGRADOURO { get; private set; }
        public string COMPLEMENTO { get; private set; }
        public string BAIRRO { get; private set; }
        public string CIDADE { get; private set; }


        public USUARIO USUARIO { get; private set; }
    }
}