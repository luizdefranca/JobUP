using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using JOB.DATA.Enum;

namespace JOB.DATA.Domain
{
    public class SERVICO
    {
        [JsonConstructor]
        protected SERVICO()
        {
            this.InicializaVariaveis();
        }

        public SERVICO(Guid ID_SERVICO, Guid ID_USUARIO, int ID_ESPECIALIDADE, int? ID_SUB_ESPECIALIDADE, bool PUBLICO, string DS_TITULO, string DS_OBSERVACOES, double? VL_SUGERIDO, EnumTempoServico TEMPO_SERVICO)
        {
            this.InicializaVariaveis();

            this.DT_CADASTRO = DateTime.Now;

            this.ID_SERVICO = ID_SERVICO;
            this.ID_USUARIO = ID_USUARIO;
            this.ID_ESPECIALIDADE = ID_ESPECIALIDADE;
            this.ID_SUB_ESPECIALIDADE = ID_SUB_ESPECIALIDADE;
            this.PUBLICO = PUBLICO;
            this.DS_TITULO = DS_TITULO;
            this.DS_OBSERVACOES = DS_OBSERVACOES;
            this.VL_SUGERIDO = VL_SUGERIDO;
            this.TEMPO_SERVICO = TEMPO_SERVICO;
        }

        public void AtualizaDados(string DS_TITULO, string DS_OBSERVACOES, double? VL_SUGERIDO, EnumTempoServico TEMPO_SERVICO)
        {
            this.DS_TITULO = DS_TITULO;
            this.DS_OBSERVACOES = DS_OBSERVACOES;
            this.VL_SUGERIDO = VL_SUGERIDO;
            this.TEMPO_SERVICO = TEMPO_SERVICO;
        }

        private void InicializaVariaveis()
        {
            this.OFERTAS = new HashSet<OFERTA_SERVICO>();
            this.PROPOSTAS = new HashSet<PROPOSTA_SERVICO>();
        }

        public Guid ID_SERVICO { get; private set; }
        public Guid ID_USUARIO { get; private set; }
        public int ID_ESPECIALIDADE { get; private set; }
        public int? ID_SUB_ESPECIALIDADE { get; private set; }
        public DateTime DT_CADASTRO { get; private set; }
        public bool PUBLICO { get; private set; }
        public string DS_TITULO { get; private set; }
        public string DS_OBSERVACOES { get; private set; }
        public double? VL_SUGERIDO { get; private set; }
        public EnumTempoServico TEMPO_SERVICO { get; private set; }

        public PERFIL_PROFISSIONAL PERFIL_PROFISSIONAL { get; private set; }

        public ICollection<OFERTA_SERVICO> OFERTAS { get; private set; }
        public ICollection<PROPOSTA_SERVICO> PROPOSTAS { get; private set; }
    }
}