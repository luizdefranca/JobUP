using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class OfertaViewModel
    {
        [Key]
        public Guid ID_SERVICO { get; set; }

        [Key]
        public Guid ID_USUARIO { get; set; }

        [Display(Name = "Data Oferta")]
        public DateTime DT_OFERTA { get; set; }

        [Display(Name = "Aceitar?")]
        public bool? ACEITA { get; set; }

        [Display(Name = "Título")]
        public string DS_TITULO { get; set; }

        [Display(Name = "Observações")]
        public string DS_OBSERVACOES { get; set; }
    }
}