using Newtonsoft.Json;
using System;

namespace JOB.DATA.Domain
{
    public class AVALIACAO
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected AVALIACAO()
        {
            this.InicializaVariaveis();
        }

        public AVALIACAO(int ID_USUARIO, int ID_ESPECIALIDADE, int ID_CLIENTE, Int16 NOTA, string COMENTARIO)
        {
            this.InicializaVariaveis();

            this.ID_USUARIO = ID_USUARIO;
            this.ID_ESPECIALIDADE = ID_ESPECIALIDADE;
            this.ID_CLIENTE = ID_CLIENTE;
            this.DT_ULT_AVALIACAO = DateTime.Now;
            this.NOTA = NOTA;
            this.COMENTARIO = COMENTARIO;
        }

        public void AtualizaDados(Int16 NOTA, string COMENTARIO)
        {
            this.DT_ULT_AVALIACAO = DateTime.Now;
            this.NOTA = NOTA;
            this.COMENTARIO = COMENTARIO;
        }

        private void InicializaVariaveis()
        {
            //this.GUIAS = new HashSet<GUIA>();
        }

        public int ID_USUARIO { get; private set; }
        public int ID_ESPECIALIDADE { get; private set; }
        public int ID_CLIENTE { get; private set; }
        public DateTime DT_ULT_AVALIACAO { get; private set; }
        public Int16 NOTA { get; private set; }
        public string COMENTARIO { get; private set; }

        public PERFIL_PROFISSIONAL PERFIL_PROFISSIONAL { get; private set; }
    }
}