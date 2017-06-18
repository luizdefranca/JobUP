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

        public AVALIACAO(Guid ID_USUARIO, int ID_ESPECIALIDADE, Guid ID_CLIENTE, Int16 NOTA, string COMENTARIO)
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

        public Guid ID_USUARIO { get; set; }
        public int ID_ESPECIALIDADE { get; set; }
        public Guid ID_CLIENTE { get; set; }
        public DateTime DT_ULT_AVALIACAO { get; set; }
        public Int16 NOTA { get; set; }
        public string COMENTARIO { get; set; }

        public PERFIL_PROFISSIONAL PERFIL_PROFISSIONAL { get; set; }
    }
}