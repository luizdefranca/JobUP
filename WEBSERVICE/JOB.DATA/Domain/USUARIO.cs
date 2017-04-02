using JOB.DATA.ValueObject;
using System;

namespace JOB.DATA.Domain
{
    public class USUARIO
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        protected USUARIO()
        {
            this.InicializaVariaveis();
        }

        public USUARIO(string NOME, CPF CPF, RG RG, DateTime DT_NASCIMENTO)
        {
            this.InicializaVariaveis();

            this.NOME = NOME;
            this.CPF = CPF;
            this.CPF = CPF;
            this.RG = RG;
            this.DT_NASCIMENTO = DT_NASCIMENTO;

            this.DT_INCLUSAO = DateTime.Now;
            this.DT_ORDENACAO = DateTime.Now;
        }

        private void InicializaVariaveis()
        {
            //this.GUIAS = new HashSet<GUIA>();
        }

        public int ID_USUARIO { get; private set; }

        public string NOME { get; private set; }

        public CPF CPF { get; private set; }

        public RG RG { get; private set; }

        public DateTime DT_NASCIMENTO { get; private set; }

        public DateTime DT_INCLUSAO { get; private set; }

        public DateTime? DT_ALTERACAO { get; private set; }

        public DateTime? DT_APROVACAO { get; private set; }

        public DateTime DT_ORDENACAO { get; private set; }

        public bool APROVADO { get; private set; }
    }
}