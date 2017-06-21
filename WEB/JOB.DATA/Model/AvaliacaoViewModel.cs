using System;
using System.ComponentModel.DataAnnotations;

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

        /// <summary>
        /// Data da ultima avaliação realizada por um determinado cliente
        /// </summary>
        [Display(Name = "Data da Avaliação")]
        public DateTime DT_ULT_AVALIACAO { get; set; }

        /// <summary>
        /// Última nota recebida como avaliação de um determinado cliente
        /// </summary>
        [Display(Name = "Nota")]
        public Int16 NOTA { get; set; }

        /// <summary>
        /// Último comentário recebido como avaliação de um determinado cliente
        /// </summary>
        [DataType(DataType.MultilineText)]
        [Display(Name = "Comentário")]
        public string COMENTARIO { get; set; }
    }
}