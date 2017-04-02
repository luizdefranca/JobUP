using AgendaCirurgicaBeta.Domain.Core;
using JOB.DATA.ValueObject;

namespace AgendaCirurgicaBeta.Domain.Entities
{
    public class CONTATO : EntityBase
    {
        //public EnumTipoContato TIPO { get; protected set; }
        public string VALOR { get; protected set; }
    }

    public class CONTATO_UNIDADE : CONTATO
    {
        protected CONTATO_UNIDADE()
        {
        }

        public CONTATO_UNIDADE(int ID_HOSPITAL, int ID_UNIDADE, string VALOR)
        {
            //this.ID_HOSPITAL = ID_HOSPITAL;
            //this.ID_UNIDADE = ID_UNIDADE;
            //base.TIPO = TIPO;
            base.VALOR = VALOR;
        }

        public void AtualizarValor(string VALOR)
        {
            base.VALOR = VALOR;
        }

        public int ID_USUARIO { get; private set; }
        public Telefone FIXO { get; set; }

        public Telefone CELULAR { get; set; }
        public Email EMAIL { get; set; }

        //public UNIDADE_HOSPITAL UNIDADE { get; private set; }
    }
}