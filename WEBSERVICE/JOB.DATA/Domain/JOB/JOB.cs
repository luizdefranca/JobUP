using System;

namespace JOB.DATA.Domain.JOB
{
    internal class JOB
    {
        public int ID_JOB { get; set; }
        public int ID_USUARIO_CLIENTE { get; set; }
        public int ID_USUARIO_PROFISSIONAL { get; set; }
        public int ID_ESPECIALIDADE { get; set; }
        public DateTime DT_JOB { get; set; }
        public String TITULO { get; set; } //100
        public String OBSERVACOES { get; set; } //1000
        public double? VALOR_SUGERIDO { get; set; }
        public bool JOB_ACEITO { get; set; }
    }
}