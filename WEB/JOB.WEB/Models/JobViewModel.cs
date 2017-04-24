using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class JobViewModel
    {
        [Key]
        public Int64 ID_JOB { get; set; }

        [Required]
        public int ID_USUARIO_CLIENTE { get; set; }

        [Required]
        public int ID_USUARIO_PROFISSIONAL { get; set; }

        [Required]
        public int ID_ESPECIALIDADE { get; set; }

        public DateTime DT_JOB { get; set; }

        [Required]
        [Display(Name = "Título")]
        public String TITULO { get; set; } //100

        [Display(Name = "Observações")]
        public String OBSERVACOES { get; set; } //1000

        [Display(Name = "Valor Sugerido")]
        public double? VALOR_SUGERIDO { get; set; }

        public bool JOB_ACEITO { get; set; }
    }
}