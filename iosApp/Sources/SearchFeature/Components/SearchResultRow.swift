//
//  File.swift
//  
//
//  Created by lease-emp-mac-suraj-sau on 2022/12/11.
//

import SwiftUI
import shared

struct SearchResultRow: View {
    
    let model: SearchResult
    
    var body: some View {
        VStack(alignment: .leading) {
            VStack(alignment: .leading, spacing: 4) {
                
                HStack(alignment: .bottom, spacing: 6) {
                    Text(self.model.value)
                        .fontWeight(.semibold)
                        .font(.system(size: 18))
                    
                    Text(self.model.reading)
                        .font(.system(size: 14))
                    
                    Spacer()
                }
                
                Text(self.model.meanings)
                    .font(.system(size: 16))
                    .opacity(0.5)
            }
            .padding()
        }
    }
}
