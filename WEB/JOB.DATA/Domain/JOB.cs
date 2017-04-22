using Newtonsoft.Json;
using System;

namespace JOB.DATA.Domain
{
    public class JOB
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected JOB()
        {
            this.InicializaVariaveis();
        }

        public JOB(Guid ID_USUARIO_CLIENTE, Guid ID_USUARIO_PROFISSIONAL, int ID_ESPECIALIDADE, DateTime DT_JOB, String TITULO, String OBSERVACOES, double? VALOR_SUGERIDO)
        {
            this.InicializaVariaveis();

            this.ID_USUARIO_CLIENTE = ID_USUARIO_CLIENTE;
            this.ID_USUARIO_PROFISSIONAL = ID_USUARIO_PROFISSIONAL;
            this.ID_ESPECIALIDADE = ID_ESPECIALIDADE;
            this.DT_JOB = DT_JOB;
            this.TITULO = TITULO;
            this.OBSERVACOES = OBSERVACOES;
            this.VALOR_SUGERIDO = VALOR_SUGERIDO;
        }

        public void Aceitar()
        {
            this.JOB_ACEITO = true;
        }

        private void InicializaVariaveis()
        {
            //this.GUIAS = new HashSet<GUIA>();
        }

        public Int64 ID_JOB { get; private set; }
        public Guid ID_USUARIO_CLIENTE { get; private set; }
        public Guid ID_USUARIO_PROFISSIONAL { get; private set; }
        public int ID_ESPECIALIDADE { get; private set; }
        public DateTime DT_JOB { get; private set; }
        public String TITULO { get; private set; } //100
        public String OBSERVACOES { get; private set; } //1000
        public double? VALOR_SUGERIDO { get; private set; }
        public bool JOB_ACEITO { get; private set; }
    }
}