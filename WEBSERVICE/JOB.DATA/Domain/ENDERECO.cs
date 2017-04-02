using AgendaCirurgicaBeta.Domain.Core;
using JOB.DATA.Enum;
using JOB.HELPERS.Validation;

namespace AgendaCirurgicaBeta.Domain.Entities
{
    public class ENDERECO : EntityBase
    {
        public int ID_USUARIO { get; set; }

        //public EnumTipoEndereco TIPO { get; protected set; }
        public EnumUF UF { get; protected set; }

        public string CEP { get; protected set; }
        public string LOGRADOURO { get; protected set; }
        public string COMPLEMENTO { get; protected set; }
        public string BAIRRO { get; protected set; }
        public string CIDADE { get; protected set; }
    }

    public class ENDERECO_MEDICO : ENDERECO
    {
        protected ENDERECO_MEDICO()
        {
        }

        public ENDERECO_MEDICO(int ID, EnumUF UF, string CEP, string LOGRADOURO, string COMPLEMENTO, string BAIRRO, string CIDADE)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(CEP, "CEP");
            AssertionConcern.AssertArgumentNotEmptyNotNull(LOGRADOURO, "LOGRADOURO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(BAIRRO, "BAIRRO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(CIDADE, "CIDADE");

            this.ID = ID;
            //base.TIPO = TIPO;
            base.UF = UF;
            base.CEP = CEP;
            base.LOGRADOURO = LOGRADOURO;
            base.COMPLEMENTO = COMPLEMENTO;
            base.BAIRRO = BAIRRO;
            base.CIDADE = CIDADE;
        }

        public void AtualizaValores(EnumUF UF, string CEP, string LOGRADOURO, string COMPLEMENTO, string BAIRRO, string CIDADE)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(CEP, "CEP");
            AssertionConcern.AssertArgumentNotEmptyNotNull(LOGRADOURO, "LOGRADOURO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(BAIRRO, "BAIRRO");
            AssertionConcern.AssertArgumentNotEmptyNotNull(CIDADE, "CIDADE");

            base.UF = UF;
            base.CEP = CEP;
            base.LOGRADOURO = LOGRADOURO;
            base.COMPLEMENTO = COMPLEMENTO;
            base.BAIRRO = BAIRRO;
            base.CIDADE = CIDADE;
        }

        public int ID { get; private set; }

        //public MEDICO MEDICO { get; private set; }
    }
}