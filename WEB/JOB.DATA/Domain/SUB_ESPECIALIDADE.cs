using JOB.HELPERS.Validation;
using Newtonsoft.Json;

namespace JOB.DATA.Domain
{
    public class SUB_ESPECIALIDADE
    {
        /// <summary>
        /// ENTITY
        /// </summary>
        [JsonConstructor]
        protected SUB_ESPECIALIDADE()
        {
            this.InicializaVariaveis();
        }

        public SUB_ESPECIALIDADE(int ID_SUB_ESPECIALIDADE, string DESCRICAO)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(DESCRICAO, "DESCRICAO");

            this.InicializaVariaveis();

            this.ID_SUB_ESPECIALIDADE = ID_SUB_ESPECIALIDADE;
            this.DESCRICAO = DESCRICAO;
        }

        public void AtualizaDados(string DESCRICAO)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(DESCRICAO, "DESCRICAO");

            this.DESCRICAO = DESCRICAO;
        }

        private void InicializaVariaveis()
        {
            //this.PERFIS_PROFISSIONAIS = new HashSet<PERFIL_PROFISSIONAL>();
        }

        public int ID_ESPECIALIDADE { get; set; }
        public int ID_SUB_ESPECIALIDADE { get; set; }
        public string DESCRICAO { get; set; }

        public ESPECIALIDADE ESPECIALIDADE { get; set; }
    }
}