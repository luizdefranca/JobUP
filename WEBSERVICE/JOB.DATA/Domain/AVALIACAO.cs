using System;

namespace JOB.DATA.Domain
{
    internal class AVALIACAO
    {
        public int ID_USUARIO { get; set; }
        public int ID_ESPECIALIDADE { get; set; }
        public int ID_CLIENTE { get; set; }
        public Int16 NOTA { get; set; }
        public String COMENTARIO { get; set; }
    }
}