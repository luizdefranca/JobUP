using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class OfertaViewModel
    {
       
        [Key]         
        public int ID_OFERTA { get; set; }

        [Required]
        public int ID_USUARIO { get; set; }

        [Required]
        [Display(Name = "Data da oferta")]
        public DateTime DT_INCLUSAO { get; set; }

        [Required]
        [Display(Name = "Data da execução")]
        public DateTime DT_JOB { get; set; }

        [Required]
        [Display(Name = "Valor")]
        public double? VALOR_SUGERIDO { get; set; }

    }
}