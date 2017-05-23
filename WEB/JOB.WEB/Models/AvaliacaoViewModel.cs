using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace JOB.WEB.Models
{
    public class AvaliacaoViewModel
    {
        [Key]
        public Guid ID_USUARIO { get; set; }

        [Key]
        public int ID_ESPECIALIDADE { get; set; }

        [Key]
        public Guid ID_CLIENTE { get; set; }

        [Required]
        public string NOME { get; set; }

        [Display(Name = "Data da Avaliação")]
        public DateTime DT_ULT_AVALIACAO { get; set; }

        [Display(Name = "Nota")]
        public Int16 NOTA { get; set; }

        [Display(Name = "Comentário")]
        public string COMENTARIO { get; set; }
    }
}