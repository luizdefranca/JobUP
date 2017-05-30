using JOB.DATA.Domain;
using System.Collections.Generic;

namespace JOB.WEB.Models
{
    public class FinalizarNegociacaoVM
    {
        public int QTD_MENSAGENS_NAO_LIDAS { get; set; }
        public List<ChatVM> Chats { get; set; }
    }
}