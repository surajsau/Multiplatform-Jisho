//
//  JlptRow.swift
//  
//
//  Created by surajsau on 2022/12/16.
//

import SwiftUI

struct JlptRow: View {

    let title: String
    let description: String

    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 8) {
                Text(self.title)
                    .font(.title3)
                    .fontWeight(.semibold)

                Text(self.description)
                    .font(.body)
                    .opacity(0.6)
            }
            Spacer()
        }
        .padding()
    }
}

struct JlptRow_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            JlptRow(title: "N5", description: "Understanding of some basic Japanese")
                .padding()
        }
    }
}
