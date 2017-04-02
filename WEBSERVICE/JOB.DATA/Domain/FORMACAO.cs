using System;

namespace JOB.DATA.Domain
{
    internal class FORMACAO
    {
        public int ID_USUARIO { get; set; }
        public int ID_ESPECIALIDADE { get; set; }
        public int ID_FORMACAO { get; set; }
        public String INSTITUICAO { get; set; }
        public String NOME_CURSO { get; set; }
        public Int16 ANO_FORMACAO { get; set; }
        public DateTime? DT_APROVACAO { get; set; }
        public bool APROVADO { get; set; }
    }
}