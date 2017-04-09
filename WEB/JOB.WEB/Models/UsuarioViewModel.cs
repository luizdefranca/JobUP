using JOB.DATA.Enum;
using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class UsuarioViewModel
    {
        [Key]
        public int ID_USUARIO { get; set; }

        [Required]
        [Display(Name = "Nome")]
        public string NOME { get; set; }

        [Required]
        [Display(Name = "CPF")]
        public string CPF { get; set; }

        [Required]
        [Display(Name = "RG (UF)")]
        public EnumUF RgUF { get; set; }

        [Required]
        [Display(Name = "RG (NR)")]
        public string RgNR { get; set; }

        [Required]
        [Display(Name = "Dt. Nascimento")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime DT_NASCIMENTO { get; set; }

        [ScaffoldColumn(false)] //ScaffoldColumn(false) = não é gerado automaticamente nas views
        public DateTime DT_INCLUSAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime? DT_ALTERACAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime? DT_APROVACAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime DT_ORDENACAO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Aprovado")]
        public bool APROVADO { get; set; }

        [DataType(DataType.PhoneNumber)]
        [Display(Name = "Telefone Fixo")]
        public string FIXO { get; set; }

        [Required]
        [DataType(DataType.PhoneNumber)]
        [Display(Name = "Telefone Celular")]
        public string CELULAR { get; set; }

        [Required]
        [DataType(DataType.EmailAddress)]
        [Display(Name = "Email")]
        public string EMAIL { get; set; }

        public EnumUF UF { get; set; }
        public string CEP { get; set; }
        public string LOGRADOURO { get; set; }
        public string COMPLEMENTO { get; set; }
        public string BAIRRO { get; set; }
        public string CIDADE { get; set; }
    }
}