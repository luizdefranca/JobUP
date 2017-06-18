using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class HistoricoMoedaVM
    {
        [Key]
        public Guid ID_USUARIO { get; set; }

        [Key]
        [Display(Name = "Dt Movimentaçao")]
        public DateTime DT_MOVIMENTACAO { get; set; }

        [Display(Name = "Valor")]
        public Int16 VALOR_MOVIMENTADO { get; set; }

        [Display(Name = "Motivo")]
        public string MOTIVO { get; set; }
    }
}