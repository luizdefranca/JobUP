using System;

namespace JOB.DATA.Domain
{
    internal class PERFIL_PROFISSIONAL
    {
        public int ID_USUARIO { get; set; }
        public int ID_ESPECIALIDADE { get; set; }
        public DateTime? DT_APROVACAO { get; set; }
        public bool APROVADO { get; set; }
        public String RESUMO_CURRICULO { get; set; }
    }
}