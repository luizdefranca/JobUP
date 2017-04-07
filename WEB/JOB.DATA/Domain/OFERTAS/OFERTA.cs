using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JOB.DATA.Domain.OFERTAS
{
    class OFERTA
    {
        public int ID_OFERTA { get; set; }
        public int ID_USUARIO { get; set; }
        public int ID_ESPECIALIDADE { get; set; }
        public DateTime DT_INCLUSAO { get; set; }
        public DateTime DT_JOB { get; set; } 
        public String TITULO { get; set; } //100
        public String OBSERVACOES { get; set; } //1000
        public double? VALOR_SUGERIDO { get; set; }
        public bool FECHADO { get; set; }
    }
}
